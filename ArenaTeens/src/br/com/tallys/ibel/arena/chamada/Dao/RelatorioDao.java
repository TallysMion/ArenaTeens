package br.com.tallys.ibel.arena.chamada.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

import br.com.tallys.ibel.arena.chamada.jdbc.Factory;
import br.com.tallys.ibel.arena.chamada.model.Fechamento;
import br.com.tallys.ibel.arena.chamada.model.Relatorio;
import br.com.tallys.ibel.arena.chamada.model.RelatorioArena;
import br.com.tallys.ibel.arena.chamada.model.RelatorioImersao;
import br.com.tallys.ibel.arena.chamada.model.Teens;
import br.com.tallys.ibel.arena.chamada.model.Enum.relatorioType;

public class RelatorioDao {
	

	public static LinkedList<Relatorio> getAllRelatorios() throws ClassNotFoundException, IOException, SQLException {

//		return null;
		
		Connection connect = null;
		connect = new Factory().getConnection();
		
		
		
		String consult = "SELECT * FROM `relatorios`";		
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		
		LinkedList<Relatorio> result = new LinkedList<>();
		
		while(resultSet.next()) {
			Relatorio r = null;			
			relatorioType tipo = resultSet.getInt(2)==0?relatorioType.relatorioArena:relatorioType.relatorioImersao;
			Teens pessoa = (Teens) UserDao.getUserById(resultSet.getInt(5));
			Fechamento fechamento = resultSet.getBoolean(3)?fechamentoDao.getFechamento(resultSet.getInt(4)):null;
			Fechamento fechamentoBim = resultSet.getBoolean(8)?fechamentoDao.getFechamento(resultSet.getInt(9)):null;
			Date data = resultSet.getDate(10);
			
			if(tipo.equals(relatorioType.relatorioArena)) {
				
				String consultB = "SELECT * FROM `rel_arena` WHERE `arena_rel_id`="+resultSet.getInt(6);
				Statement statementB = connect.createStatement();
				ResultSet resultSetB = statementB.executeQuery(consultB);
				if(resultSetB.next()) {
				r = new RelatorioArena(
						data, 
						fechamento,
						fechamentoBim,
						pessoa,
						resultSetB.getBoolean(2),
						resultSetB.getBoolean(3),
						resultSetB.getBoolean(4),
						resultSetB.getBoolean(5),
						resultSetB.getBoolean(6),
						resultSetB.getInt(7),
						resultSetB.getInt(8));
				}
			}
			
			if(tipo.equals(relatorioType.relatorioImersao)) {
				String consultB = "SELECT * FROM `rel_arena` WHERE `arena_rel_id`="+resultSet.getInt(6);
				Statement statementB = connect.createStatement();
				ResultSet resultSetB = statementB.executeQuery(consultB);
				if(resultSetB.next()) {
				r = new RelatorioImersao(
						data, 
						fechamento,
						fechamentoBim,
						pessoa,
						resultSetB.getBoolean(2),
						resultSetB.getInt(3));
				}
			}
						
			result.add(r);			
			
		}
		connect.close();
		return result;
		
	}

	//causa erro no login do admin
	public static LinkedList<Relatorio> getRelatorios(int idTeen) throws SQLException, ClassNotFoundException, IOException {

//		return null;
		
		Connection connect = null;
		connect = new Factory().getConnection();
		
		String consult = "SELECT * FROM `relatorios` WHERE `rel_idteen`="+idTeen;		
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		
		LinkedList<Relatorio> result = new LinkedList<>();
		
		while(resultSet.next()) {
			Relatorio r = null;
			
			relatorioType tipo = resultSet.getInt(2)==0?relatorioType.relatorioArena:relatorioType.relatorioImersao;
			Fechamento fechamento = resultSet.getBoolean(3)?fechamentoDao.getFechamento(resultSet.getInt(4)):null;
			Fechamento fechamentoBim = resultSet.getBoolean(8)?fechamentoDao.getFechamento(resultSet.getInt(9)):null;
			Date data = resultSet.getDate(10);
			
			if(tipo.equals(relatorioType.relatorioArena)) {
				
				String consultB = "SELECT * FROM `rel_arena` WHERE `arena_rel_id`="+resultSet.getInt(6);
				Statement statementB = connect.createStatement();
				ResultSet resultSetB = statementB.executeQuery(consultB);
				if(resultSetB.next()) {
				r = new RelatorioArena(
						data, 
						fechamento,
						fechamentoBim,
						null,
						resultSetB.getBoolean(2),
						resultSetB.getBoolean(3),
						resultSetB.getBoolean(4),
						resultSetB.getBoolean(5),
						resultSetB.getBoolean(6),
						resultSetB.getInt(7),
						resultSetB.getInt(8));
				}
				
			}
			
			if(tipo.equals(relatorioType.relatorioImersao)) {
				String consultB = "SELECT * FROM `rel_arena` WHERE `arena_rel_id`="+resultSet.getInt(6);
				Statement statementB = connect.createStatement();
				ResultSet resultSetB = statementB.executeQuery(consultB);
				if(resultSetB.next()) {
				r = new RelatorioImersao(
						data, 
						fechamento,
						fechamentoBim,
						null,
						resultSetB.getBoolean(2),
						resultSetB.getInt(3));
				}
			}
						
			result.add(r);			
			
		}
		connect.close();
		return result;
		
	}
	
	//passou no login admin
	public static LinkedList<Relatorio> getRelatoriosFechamentoAnual(int idFech) throws SQLException, ClassNotFoundException, IOException {
		
//		return null;
		
		Connection connect = null;
		connect = new Factory().getConnection();
		
		String consult = "SELECT * FROM `relatorios` WHERE `rel_isclosed`=1 AND `rel_idfech`="+idFech;		
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		
		LinkedList<Relatorio> result = new LinkedList<>();
		
		while(resultSet.next()) {
			Relatorio r = null;
			
			relatorioType tipo = resultSet.getInt(2)==0?relatorioType.relatorioArena:relatorioType.relatorioImersao;
			Teens pessoa = (Teens) UserDao.getUserById(resultSet.getInt(5));
			Fechamento fechamento = resultSet.getBoolean(3)?fechamentoDao.getFechamento(resultSet.getInt(4)):null;
			Fechamento fechamentoBim = resultSet.getBoolean(8)?fechamentoDao.getFechamento(resultSet.getInt(9)):null;
			Date data = resultSet.getDate(10);
			
			if(tipo.equals(relatorioType.relatorioArena)) {
				
				String consultB = "SELECT * FROM `rel_arena` WHERE `arena_rel_id`="+resultSet.getInt(6);
				Statement statementB = connect.createStatement();
				ResultSet resultSetB = statementB.executeQuery(consultB);
				if(resultSetB.next()) {
				r = new RelatorioArena(
						data, 
						fechamento,
						fechamentoBim,
						pessoa,
						resultSetB.getBoolean(2),
						resultSetB.getBoolean(3),
						resultSetB.getBoolean(4),
						resultSetB.getBoolean(5),
						resultSetB.getBoolean(6),
						resultSetB.getInt(7),
						resultSetB.getInt(8));
				}
			}
			
			if(tipo.equals(relatorioType.relatorioImersao)) {
				String consultB = "SELECT * FROM `rel_arena` WHERE `arena_rel_id`="+resultSet.getInt(6);
				Statement statementB = connect.createStatement();
				ResultSet resultSetB = statementB.executeQuery(consultB);
				if(resultSetB.next()) {
				r = new RelatorioImersao(
						data, 
						fechamento,
						fechamentoBim,
						pessoa,
						resultSetB.getBoolean(2),
						resultSetB.getInt(3));
				}
			}
						
			result.add(r);			
			
		}
		connect.close();
		return result;
		
	}

	//passou no login adm
	public LinkedList<Relatorio> getRelatoriosFechamentoBim(int idFech) throws ClassNotFoundException, IOException, SQLException {
//		return null;
		
		Connection connect = null;
		connect = new Factory().getConnection();
		
		String consult = "SELECT * FROM `relatorios` WHERE `rel_isclosedB`=1 AND `rel_idfechB`="+idFech;		
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(consult);
		
		LinkedList<Relatorio> result = new LinkedList<>();
		
		while(resultSet.next()) {
			Relatorio r = null;
			
			relatorioType tipo = resultSet.getInt(2)==0?relatorioType.relatorioArena:relatorioType.relatorioImersao;
			Teens pessoa = (Teens) UserDao.getUserById(resultSet.getInt(5));
			Fechamento fechamento = resultSet.getBoolean(3)?fechamentoDao.getFechamento(resultSet.getInt(4)):null;
			Fechamento fechamentoBim = resultSet.getBoolean(8)?fechamentoDao.getFechamento(resultSet.getInt(9)):null;
			Date data = resultSet.getDate(10);
			
			if(tipo.equals(relatorioType.relatorioArena)) {
				
				String consultB = "SELECT * FROM `rel_arena` WHERE `arena_rel_id`="+resultSet.getInt(6);
				Statement statementB = connect.createStatement();
				ResultSet resultSetB = statementB.executeQuery(consultB);
				if(resultSetB.next()) {
				r = new RelatorioArena(
						data, 
						fechamento,
						fechamentoBim,
						pessoa,
						resultSetB.getBoolean(2),
						resultSetB.getBoolean(3),
						resultSetB.getBoolean(4),
						resultSetB.getBoolean(5),
						resultSetB.getBoolean(6),
						resultSetB.getInt(7),
						resultSetB.getInt(8));
				}
			}
			
			if(tipo.equals(relatorioType.relatorioImersao)) {
				String consultB = "SELECT * FROM `rel_arena` WHERE `arena_rel_id`="+resultSet.getInt(6);
				Statement statementB = connect.createStatement();
				ResultSet resultSetB = statementB.executeQuery(consultB);
				if(resultSetB.next()) {
				r = new RelatorioImersao(
						data, 
						fechamento,
						fechamentoBim,
						pessoa,
						resultSetB.getBoolean(2),
						resultSetB.getInt(3));
				}
			}
						
			result.add(r);			
			
		}
		connect.close();
		return result;
		
	}
	
	public static void addRelatorio(Relatorio rl) throws ClassNotFoundException, IOException, SQLException {
		Connection connect = null;
		connect = new Factory().getConnection();			
		
		String consult;
		PreparedStatement pstmt;
		int extId = -1;
		
		if(rl.getTipo().equals(relatorioType.relatorioArena)) {
			RelatorioArena ra = (RelatorioArena) rl;
			
			consult = "INSERT INTO `rel_arena`(`arena_rel_presenca`, "
					+ "`arena_rel_meditacao`, `arena_rel_pontualidade`, "
					+ "`arena_rel_anotDom`, `arena_rel_anotArena`, "
					+ "`arena_rel_versiculos`, `arena_rel_extra`) "
					+ "VALUES (?,?,?,?,?,?,?)";
			
			pstmt = connect.prepareStatement(consult, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBoolean(1, ra.isPresenca());
			pstmt.setBoolean(2, ra.isMeditacao());
			pstmt.setBoolean(3, ra.isPontualidade());
			pstmt.setBoolean(4, ra.isAnotDomingo());
			pstmt.setBoolean(5, ra.isAnotArena());
			pstmt.setInt(6, ra.getVersiculos());
			pstmt.setInt(7, ra.getPontosExtras());
			pstmt.executeUpdate();		
			
			final ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
			    extId = rs.getInt(1);
			}
		
		
		}else {
			
			RelatorioImersao ri = (RelatorioImersao) rl;
			
			consult = "INSERT INTO `rel_imersao`("
					+ " `imer_rel_presenca`,"
					+ " `imer_rel_extra`) "
					+ "VALUES (?,?)";
			
			pstmt = connect.prepareStatement(consult, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBoolean(1, ri.isPresenca());
			pstmt.setInt(2, ri.getPontosExtras());
			pstmt.executeUpdate();		
			
			final ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
			    extId = rs.getInt(1);
			}
		}
				
		consult = "INSERT INTO `relatorios`(`rel_type`, `rel_isclosed`, `rel_idfech`, `rel_idteen`, `rel_pont`, `rel_isclosedB`, `rel_idfechB`, `data`, `rel_extid`) VALUES (?,?,?,?,?,?,?,?,?)";
		pstmt = connect.prepareStatement(consult);
		pstmt.setInt(1, rl.getTipo().ordinal());
		pstmt.setInt(2, 0);
		pstmt.setInt(3, -1);
		pstmt.setInt(4, rl.getPessoa().getId());
		pstmt.setInt(5, rl.getPontuacao());
		pstmt.setInt(6, 0);
		pstmt.setInt(7, -1);
		pstmt.setDate(8, new java.sql.Date(rl.getData().getTime()));
		pstmt.setInt(9, extId);

		pstmt.executeUpdate();
		connect.close();
		return;
	}

	

}
